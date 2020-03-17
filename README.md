This project aims to transform a Spark's simple string schema (printed when called printSchema method in a DataFrame) 
into a source code of a StructType based on the schema. Furthermore, this is a learning project of some Scala's 
fundamentals and reading the DataFrame directly from the data source could be achieved the goal,  however, that 
would not be so useful for my learning purposes.

**How to use:**
- Run the project passing the input text file that contains the string schema as a parameter, example: src/main/resources/schema.txt

**Input example:**  
root  
 |-- topic: string (nullable = true)  
 |-- key: string (nullable = true)  
 |-- received_iso_at: timestamp (nullable = true)  
 
 **Output example:**  
 StructType(   
 Array(  
  StructField("topic", StringType, nullable = true),  
  StructField("key", StringType, nullable = true),  
  StructField("received_iso_at", TimestampType, nullable = true),  
 )  
 )  
