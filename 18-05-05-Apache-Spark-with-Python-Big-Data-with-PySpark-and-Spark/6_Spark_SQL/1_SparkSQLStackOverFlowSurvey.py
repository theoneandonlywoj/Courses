from pyspark.sql import SparkSession

AGE_MIDPOINT = "age_midpoint"
SALARY_MIDPOINT = "salary_midpoint"
SALARY_MIDPOINT_BUCKET = "salary_midpoint_bucket"

if __name__ == "__main__":
	# Create a Spark sessuib
    session = SparkSession.builder.appName("StackOverFlowSurvey").getOrCreate()

    # Create a dataFrame reader
    dataFrameReader = session.read

    # Read the data with the header so Spark will not read it as a regular line
    # Option "inferSchema" set to true allows Spark to create a schema based on the headers
    responses = dataFrameReader \
        .option("header", "true") \
        .option("inferSchema", value = True) \
        .csv("data/2016-stack-overflow-survey-responses.csv")

    print("=== Print out schema ===")
    responses.printSchema()
    
    # Selecting chosing columns
    responseWithSelectedColumns = responses.select("country", "occupation", 
        AGE_MIDPOINT, SALARY_MIDPOINT)

    # Showing the selected columns
    print("=== Print the selected columns of the table ===")
    responseWithSelectedColumns.show()

    print("=== Print records where the response is from Denmark ===")
    # Filtering and showing values when the column country is Poland
    responseWithSelectedColumns\
        .filter(responseWithSelectedColumns["country"] == "Denmark").show()

    print("=== Print the count of occupations ===")
    # Grouping by the occupationg and counting number of values per group
    groupedData = responseWithSelectedColumns.groupBy("occupation")
    groupedData.count().show()

    print("=== Print records with average mid age less than 20 ===")
    # Filtering out certain value based on a requirement
    responseWithSelectedColumns.filter(responseWithSelectedColumns[AGE_MIDPOINT] < 20).show()

    print("=== Print the result by salary middle point in descending order ===")
    responseWithSelectedColumns.orderBy(responseWithSelectedColumns[SALARY_MIDPOINT], ascending = False).show()

    print("=== Group by country and aggregate by average salary middle point ===")
    dataGroupByCountry = responseWithSelectedColumns.groupBy("country")
    dataGroupByCountry.avg(SALARY_MIDPOINT).show()

    # Salary buckets
    # 45k will be in a bracket 40k - 60k
    responseWithSalaryBucket = responses.withColumn(SALARY_MIDPOINT_BUCKET, ((responses[SALARY_MIDPOINT]/20000).cast("integer")*20000))

    print("=== With salary bucket column ===")
    responseWithSalaryBucket.select(SALARY_MIDPOINT, SALARY_MIDPOINT_BUCKET).show()

    print("=== Group by salary bucket ===")
    responseWithSalaryBucket \
        .groupBy(SALARY_MIDPOINT_BUCKET) \
        .count() \
        .orderBy(SALARY_MIDPOINT_BUCKET) \
        .show()

    # Finally, stopping the session
    session.stop()
