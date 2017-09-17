import pandas as pd 
import matplotlib.pyplot as plt 

def import_data(file, print_head = False):
	df = pd.read_csv('data/{}.csv'.format(file))
	print(file)
	if print_head == True:
		print("First few values:")
		print(df.head())
	return df

def print_max_close(df):
	close_prices = df['Volume']
	max_close = close_prices.max()
	print(max_close)

def print_mean_volume(df):
	volume = df['Volume']
	mean_volume = volume.mean()
	print(mean_volume)
def plot_adj_close(df):
	df['Adj Close'].plot()
	plt.show()

if __name__ == '__main__':
	for company in ['AAPL']:
		df = import_data(company)
		plot_adj_close(df)
	print("DONE!")