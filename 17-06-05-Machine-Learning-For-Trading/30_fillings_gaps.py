import os
import pandas as pd 
import matplotlib.pyplot as plt 

def symbol_to_path(symbol, base_dir = 'data'):
	return os.path.join(base_dir, "{}.csv".format(str(symbol)))

def dates_creator():
	start_date = '2009-01-01'
	end_date = '2011-12-31'
	dates = pd.date_range(start_date, end_date)
	return dates

def get_data(symbols, dates):
	df = pd.DataFrame(index = dates)
	if 'SPY' not in symbols: # adding SPY as the main reference
		symbols.insert(0, 'SPY')
	for symbol in symbols:
		df_temp = pd.read_csv(symbol_to_path(symbol),
			index_col = 'Date',
			parse_dates = True,
			usecols = ['Date', 'Adj Close'],
			na_values = ['nan'])
		df_temp = df_temp.rename(columns = {'Adj Close': symbol})
		df = df.join(df_temp)
	
		if symbol == 'SPY':
			df = df.dropna(subset = ['SPY'])
	return df
	print(df)
def fill_gaps(df):
	# First filling forward to avoid peeking to the future
	df.fillna(method = 'ffill', inplace = 'TRUE')
	# Then fillin backwards
	df.fillna(method = 'bfill', inplace = 'TRUE')
	return df
	
def plot_data(df, title):
	ax = df.plot(title = title, fontsize = 10)
	ax.set_xlabel('Date')
	ax.set_ylabel('Stock price')
	plt.show()


symbols = ['SPY', 'TSLA']

if __name__ == "__main__":
	dates = dates_creator()
	df = get_data(symbols, dates)
	# Before filling the gaps
	plot_data(df, 'Before')
	# Filling the gaps
	df = fill_gaps(df)
	plot_data(df, 'After')