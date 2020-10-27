mvn package

spark-submit --class ru.bmstu.iu9.lab3.Main --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar