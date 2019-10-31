package com.mrgan.demo.lire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.utils.LuceneUtils;

/**
 * Simple class showing the process of indexing
 * 
 * @author Mathias Lux, mathias@juggle.at and Nektarios Anagnostopoulos,
 *         nek.anag@gmail.com
 */
public class Indexer {
	public static void main(String[] args) throws IOException {
		String path = "E:\\projects\\html\\stream_projects\\新建文件夹\\images\\image";
		// Checking if arg[0] is there and if it is a directory.
		// boolean passed = false;
		// if (args.length > 0) {
		// File f = new File(args[0]);
		// System.out.println("Indexing images in " + args[0]);
		// if (f.exists() && f.isDirectory())
		// passed = true;
		// }
		// if (!passed) {
		// System.out.println("No directory given as first argument.");
		// System.out.println("Run \"Indexer <directory>\" to index files of a
		// directory.");
		// System.exit(1);
		// }
		// Getting all images from a directory and its sub directories.
		File imagePath = new File(path);
		ArrayList<String> images = new ArrayList();
		for (File image : imagePath.listFiles()) {
			System.out.println(image.getPath());
			images.add(image.getPath());
		}
		// ArrayList<String> images = FileUtils.readFileLines(new File(args[0]),
		// true);

		// Creating a CEDD document builder and indexing all files.
		GlobalDocumentBuilder globalDocumentBuilder = new GlobalDocumentBuilder(CEDD.class);
		// Creating an Lucene IndexWriter
		IndexWriter iw = LuceneUtils.createIndexWriter("E:\\projects\\index\\imageIndex", true,
				LuceneUtils.AnalyzerType.WhitespaceAnalyzer);
		// Iterating through images building the low level features
		for (Iterator<String> it = images.iterator(); it.hasNext();) {
			String imageFilePath = it.next();
			System.out.println("Indexing " + imageFilePath);
			try {
				BufferedImage img = ImageIO.read(new FileInputStream(imageFilePath));
				Document document = globalDocumentBuilder.createDocument(img, imageFilePath);
				iw.addDocument(document);
			} catch (Exception e) {
				System.err.println("Error reading image or indexing it.");
				e.printStackTrace();
			}
		}
		// closing the IndexWriter
		LuceneUtils.closeWriter(iw);
		System.out.println("Finished indexing.");
	}
}