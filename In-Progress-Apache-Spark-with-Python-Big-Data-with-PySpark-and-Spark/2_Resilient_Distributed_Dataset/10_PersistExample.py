from pyspark import SparkContext, SparkConf, StorageLevel

if __name__ == "__main__":
    conf = SparkConf().setAppName("persist").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    inputIntegers = [1, 2, 3, 4, 5]
    integerRdd = sc.parallelize(inputIntegers)
    
    # Persist is used when the RDD is meant to be reused.
    # In this scenario, the RDD is computed with the first call
    # and will be kept in memory across the nodes afterwards.
    # Each RDD can be persisted using different storage level:
    # - MEMORY_ONLY
    # - MEMORY_AND_DISK
    # - MEMORY_ONLY_SER (Java and Scala)
    # - MEMORY_AND_DISK_SER (Java and Scala)
    # - DISK_ONLY
    # 
    # Default persisting method is MEMORY_ONLY and can be called
    # By invoking .cache.
    # f.e
    # integerRdd.cache() 
    # or
    # integerRdd.persist(MEMORY_ONLY)
    
    integerRdd.persist(StorageLevel.MEMORY_ONLY)
    
    integerRdd.reduce(lambda x, y: x * y)
    
    integerRdd.count()
