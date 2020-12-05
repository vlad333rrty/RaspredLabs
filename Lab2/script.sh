mvn package

hadoop fs -rm airports.csv
hadoop fs -rm flights.csv
hdfs dfs -rm -r output

hadoop fs -copyFromLocal airports.csv
hadoop fs -copyFromLocal flights.csv

export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar

rm output -r

hadoop ru.bmstu.iu9.lab2.Main data.csv airports.csv output
hadoop fs -copyToLocal output
