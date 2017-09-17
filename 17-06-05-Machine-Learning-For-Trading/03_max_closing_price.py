import pandas as pd 

def import_data(file, print_head = False):
	df = pd.read_csv('data/{}.csv'.format(file))
	print(file)
	if print_head == True:
		print("First few values:")
		print(df.head())
	return df

def print_max_close(df):
	close_prices = df['Close']
	max_close = close_prices.max()
	print(max_close)

if __name__ == '__main__':
	for company in ['AAPL', "IBM"]:
		df = import_data(company)
		print_max_close(df)
	print("DONE!")