mvn package

hadoop fs -rm airports.csv
hadoop fs -rm data.csv
hdfs dfs -rm -r output

hadoop fs -copyFromLocal airports.csv
hadoop fs -copyFromLocal data.csv

~/spark-2.4.7-bin-hadoop2.7/bin/spark-submit --class Main --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar

hadoop fs -copyToLocal result