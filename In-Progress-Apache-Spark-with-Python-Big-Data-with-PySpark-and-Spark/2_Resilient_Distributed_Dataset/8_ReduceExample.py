from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("reduce").setMaster("local[*]")
    sc = SparkContext(conf = conf)
   
    inputIntegers = [1, 2, 3, 4, 5]
    integerRdd = sc.parallelize(inputIntegers)
   
    # Reduce reduces the elements of the RDD using the specified commutative and associative binary operator.
    # It take function that operates on two elements of the type in the input RDD and returns a new element of the same type.
    product = integerRdd.reduce(lambda x, y: x * y)
    print("Product is :{}".format(product))
