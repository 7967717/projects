package io.rr.tasks;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.util.List;
import java.util.regex.Pattern;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapColumnTo;

/**
 * @author roman on 3/5/17.
 */
public class F1000 {
    private static final String SERVER = "127.0.0.1";
    private static final String KEYSPACE = "kaggle";
    private static final String TABLE = "rev";
    //    Path to csv file in disk
    private static final String FILE = "/local/bigdata/Rev.csv";

    private static final Pattern SPACE = Pattern.compile(" ");
    private static final int TOP1000 = 1000;

    private static Cluster cluster = Cluster.builder().addContactPoint(SERVER).build();

    public static void main(String[] args) {
        createKeyspace();
        createTable();
        copyCSVtoTable();

//      Prints 1000 most active users
        for (Tuple2<?,?> tuple : takeX(TOP1000)) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }

    }

    private static List<Tuple2<Integer, String>> takeX(int x) {

        SparkConf conf = new SparkConf()
                .setAppName("Task").setMaster("local[*]");

        SparkContext sc = new SparkContext(conf);

        JavaRDD<String> cassandraRdd = CassandraJavaUtil.javaFunctions(sc)
                .cassandraTable(KEYSPACE, TABLE, mapColumnTo(String.class))
                .select("profile_name");

        JavaPairRDD<String, Integer> ones = cassandraRdd.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        JavaPairRDD<Integer, String> swapped = counts.mapToPair(Tuple2::swap);

        JavaPairRDD<Integer, String> sorted = swapped.sortByKey(false);

        return sorted.take(x);

    }

    private static void copyCSVtoTable() {
        String query = "COPY " + TABLE + " FROM '" + FILE + "' WITH HEADER=true";
        Session session = cluster.connect(KEYSPACE);
        session.execute(query);
    }

    private static void createTable() {
        String query = "CREATE TABLE " + TABLE + " (\n" +
                "  id INT,\n" +
                "  product_id TEXT,\n" +
                "  user_id TEXT,\n" +
                "  profile_name TEXT,\t\n" +
                "  helpfulness_numerator INT,\n" +
                "  helpfulness_denominator INT,\n" +
                "  score INT,\n" +
                "  time TIMESTAMP,\n" +
                "  summary TEXT,\n" +
                "  text TEXT,\n" +
                "  PRIMARY KEY ((id), product_id, user_id, profile_name, helpfulness_numerator, helpfulness_denominator, score, time, summary, text)\n" +
                ");";

        Session session = cluster.connect(KEYSPACE);
        session.execute(query);
    }

    private static void createKeyspace() {

        String query = "CREATE KEYSPACE " + KEYSPACE + " WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':1};";
        Session session = cluster.connect();
        session.execute(query);
    }

}
