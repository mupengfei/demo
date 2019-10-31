package com.mrgan.demo.lire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;

public class Searcher {
	public static void main(String[] args) throws IOException {
		// Checking if arg[0] is there and if it is an image.
		String imagePath = "E:\\projects\\html\\stream_projects\\新建文件夹\\images\\image\\fec925fb478b96540cc349f9be1f20bc.jpg";
		BufferedImage img = null;
		boolean passed = false;
//		if (args.length > 0) {
			File f = new File(imagePath);
			if (f.exists()) {
				try {
					img = ImageIO.read(f);
					passed = true;
				} catch (IOException e) {
					e.printStackTrace(); // To change body of catch statement
											// use File | Settings | File
											// Templates.
				}
			}
//		}
//		if (!passed) {
//			System.out.println("No image given as first argument.");
//			System.out.println("Run \"Searcher <query image>\" to search for <query image>.");
//			System.exit(1);
//		}

		IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("E:\\projects\\index\\imageIndex")));
		ImageSearcher searcher = new GenericFastImageSearcher(30, CEDD.class);

		// searching with a image file ...
		ImageSearchHits hits = searcher.search(img, ir);
		// searching with a Lucene document instance ...
		for (int i = 0; i < hits.length(); i++) {
			String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
			System.out.println(hits.score(i) + ": \t" + fileName);
		}
	}
}
