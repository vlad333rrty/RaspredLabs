mvn package



~/spark-2.4.7-bin-hadoop2.7/bin/spark-submit --class Main --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar