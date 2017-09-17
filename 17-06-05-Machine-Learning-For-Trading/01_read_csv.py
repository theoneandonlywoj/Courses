import pandas as pd 

def test_import():
	df = pd.read_csv('data/AAPL.csv')
	print(df.head()) # Printing top 5 values

	print(df.tail()) # Printing last 5 values

if __name__ == '__main__':
	test_import()
	print("DONE!")