mvn package

hadoop fs -rm airports.csv
hadoop fs -rm data.csv
hadoop fs -rm -r --ignore-fail-on-non-empty output

hadoop fs -copyFromLocal airports.csv
hadoop fs -copyFromLocal data.csv

export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar

rm output -r

hadoop Main data.csv airports.csv output
hadoop fs -copyToLocal output
