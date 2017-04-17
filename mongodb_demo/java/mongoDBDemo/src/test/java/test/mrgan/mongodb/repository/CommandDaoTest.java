package test.mrgan.mongodb.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrgan.mongodb.Application;
import com.mrgan.mongodb.model.Command;
import com.mrgan.mongodb.repository.CommandDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:/config/test.properties")
public class CommandDaoTest {

	@Autowired
	private CommandDao commandDao;

	@Test
	public void findAll() throws Exception {
		// System.out.println(commandDao.count());
		// System.out.println(commandDao.countByExitValue(0));
		// System.out.println(commandDao.countByExitValueNot(0));
		File file = new File("e://245w.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter wirter = new BufferedWriter(new FileWriter(file));
		Gson gson = new Gson();
		long startTimeSt = 0L;
		long startTimeEd = 1482428349582L;
		// Command command =
		// commandDao.findOne("e8106b31-3062-401a-afe6-abdaedaf6ee4");
		int i = 0;
		// List<Command> list = commandDao.findByExitValueNot(0);
		List<Command> list = null;
		do {
			list = commandDao.findByExitValueAndStartTimeBetween(0, startTimeSt, startTimeEd,
					new Sort(Sort.Direction.ASC, "startTime"));
			for (Command command : list) {
				String id = command.getId();
				String info = command.getInfoMessage();
				try {
					Map<String, Map<String, Object>> root = gson.fromJson(info, new TypeToken<Map<String, Object>>() {
					}.getType());
					if (root == null) {
						System.out.println(id);
						continue;
					}
					Map<String, Object> format = (Map<String, Object>) root.get("format");
					if (format == null) {
						System.out.println(id);
						continue;
					}
					Object duration = format.get("duration");
					if (duration == null) {
						System.out.println(id);
						continue;
					}
					wirter.write(id + "|||" + duration.toString());
					wirter.newLine();
					wirter.flush();
					startTimeSt = command.getStartTime();
					i++;
					// System.out.println(format.get("duration"));
				} catch (Exception e) {
					System.out.println(id);
					continue;
				}
			}
			startTimeEd = startTimeSt + 6000000L;
			System.out.println(i + "    " + startTimeEd);
		} while (list.size() != 0);
		wirter.close();
		System.out.println(i);
		// String duration = root.get("format").get("duration").toString();
	}
}
