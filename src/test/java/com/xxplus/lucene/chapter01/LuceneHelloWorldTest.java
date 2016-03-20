package com.xxplus.lucene.chapter01;

import com.xxplus.base.BaseTest;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;

/**
 * Created by admin on 2016-03-15.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class LuceneHelloWorldTest extends BaseTest{

    @Test
    public void lucene() throws IOException, ParseException {

        //词法分析器
        Analyzer analyzer = new StandardAnalyzer();

        //确定索引文件存储的位置，Lucene提供给我们两种方式：
        Directory directory = new RAMDirectory();
        //Directory directory = FSDirectory.open("/tmp/testindex");

        //创建IndexWriter，进行索引文件的写入。
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //内容提取，进行索引的存储。
        Document document = new Document();//申请了一个document对象，这个类似于数据库中的表中的一行。
        String text = "This is the text to be indexed.";//索引的字符串。
        //把字符串存储起来（因为设置了TextField.TYPE_STORED,如果不想存储，可以使用其他参数，详情参考官方文档），并存储“表明”为"fieldname".
        document.add(new Field("fieldname", text, TextField.TYPE_STORED));
        indexWriter.addDocument(document);//把doc对象加入到索引创建中
        indexWriter.close();//关闭IndexWriter,提交创建内容。


        //第一步，打开存储位置
        DirectoryReader reader = DirectoryReader.open(directory);

        //第二步，创建搜索器
        IndexSearcher searcher = new IndexSearcher(reader);

        //第三步，类似SQL，进行关键字查询
        QueryParser parser = new QueryParser("filename", analyzer);
        Query query = parser.parse("text");

        ScoreDoc[] scoreDocs = searcher.search(query, 1000).scoreDocs;

        for(ScoreDoc scoreDoc : scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            logger.info("text : {}", doc.get("filename"));
        }
        reader.close();
        directory.close();
    }


    @Test
    public void helloWorld01() throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();

        // Store the index in memory:
        Directory directory = new RAMDirectory();

        // To store an index on disk, use this instead:
        //Directory directory = FSDirectory.open("/tmp/testindex");
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new Field("fieldname", "This is the txt to be indexed.", TextField.TYPE_STORED));
        doc.add(new Field("fieldname", "This is the text to be indexed.", TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
        Assert.assertEquals(1, hits.length);
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            logger.info("text : {}", hitDoc.get("fieldname"));
//            Assert.assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }

    @Test
    public void helloWorld02() throws IOException {
        File   dataDir  = new File("D:\\luceneData");
        Analyzer luceneAnalyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(luceneAnalyzer);
        File[] dataFiles  = dataDir.listFiles();

        Directory directory = FSDirectory.open(Paths.get("D:\\luceneIndex"));
        IndexWriter indexWriter = new IndexWriter(directory, config);

        for(int i = 0; i < dataFiles.length; i++){
            if(dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")){
                logger.info("index file : {}", dataFiles[i].getCanonicalPath());

                Document document = new Document();
                Reader txtReader = new FileReader(dataFiles[i]);
                document.add(new TextField("path", dataFiles[i].getCanonicalPath(), Field.Store.YES));
                document.add(new StringField("fileName", dataFiles[i].getName(), Field.Store.YES));
                document.add(new Field("contents", txtReader, TextField.TYPE_NOT_STORED));
                indexWriter.addDocument(document);
            }
        }
        indexWriter.close();
        directory.close();
    }

    @Test
    public void helloWorld03() throws IOException, ParseException {
        Analyzer luceneAnalyzer = new StandardAnalyzer();

        Directory directory = FSDirectory.open(Paths.get("D:\\luceneIndex"));
        DirectoryReader reader = DirectoryReader.open(directory);

        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("fileName", luceneAnalyzer);
        Query query = parser.parse("document - 副本.txt");

        ScoreDoc[] scoreDocs = searcher.search(query, 1000).scoreDocs;

        logger.info("searcher result : {}", scoreDocs.length);

        for(ScoreDoc scoreDoc : scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            logger.info("path : {}", doc.get("path"));
            logger.info("fileName : {}", doc.get("fileName"));
        }
        reader.close();
        directory.close();
    }
}

