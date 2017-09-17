import pandas as pd 

def test_import():
	df = pd.read_csv('data/AAPL.csv')
	print(df[10:21]) # Printing values with indexes from 10 to 20

if __name__ == '__main__':
	test_import()
	print("DONE!")