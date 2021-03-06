mvn package

hadoop fs -rm airports.csv
hadoop fs -rm flights.csv
hdfs dfs -rm -r result

hadoop fs -copyFromLocal airports.csv
hadoop fs -copyFromLocal flights.csv

~/spark-2.4.7-bin-hadoop2.7/bin/spark-submit --class ru.bmstu.iu9.lab3.Main --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar

rm -r result

hadoop fs -copyToLocal result