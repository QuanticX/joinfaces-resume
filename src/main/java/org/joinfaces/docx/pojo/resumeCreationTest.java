package org.joinfaces.docx.pojo;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

public class resumeCreationTest {

	@Test
	public void createResumeTest() throws IOException {
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph title = document.createParagraph();

		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Build Your REST API with Spring");
		titleRun.setColor("009933");
		titleRun.setBold(true);
		titleRun.setFontFamily("Courier");
		titleRun.setFontSize(20);

		XWPFParagraph subTitle = document.createParagraph();
		subTitle.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun subTitleRun = subTitle.createRun();
		subTitleRun.setText("from HTTP fundamentals to API Mastery");
		subTitleRun.setColor("00CC44");
		subTitleRun.setFontFamily("Courier");
		subTitleRun.setFontSize(16);
		subTitleRun.setTextPosition(20);
		subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

		XWPFParagraph para1 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.BOTH);
		String string1 = "convertTextFileToString(paragraph1)";
		XWPFRun para1Run = para1.createRun();
		para1Run.setText(string1);

		XWPFParagraph para2 = document.createParagraph();
		para2.setAlignment(ParagraphAlignment.RIGHT);
		String string2 = "convertTextFileToString(paragraph2)";
		XWPFRun para2Run = para2.createRun();
		para2Run.setText(string2);
		para2Run.setItalic(true);

		XWPFParagraph para3 = document.createParagraph();
		para3.setAlignment(ParagraphAlignment.LEFT);
		String string3 = "convertTextFileToString(paragraph3)";
		XWPFRun para3Run = para3.createRun();
		para3Run.setText(string3);

		FileOutputStream out = new FileOutputStream("output.docx");
		document.write(out);
		out.close();
		document.close();

	}

}
