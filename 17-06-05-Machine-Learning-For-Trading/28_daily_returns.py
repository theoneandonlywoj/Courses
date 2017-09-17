import os
import pandas as pd 
import matplotlib.pyplot as plt 

''' Read: http://pandas.pydata.org/pandas-docs/stable/api.html#api-dataframe-stats '''

def symbol_to_path(symbol, base_dir = 'data'):
	return os.path.join(base_dir, "{}.csv".format(str(symbol)))

def dates_creator():
	start_date = '2013-01-01'
	end_date = '2013-12-31'
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

	print(df)
	return df

def plot(df, title):
	ax = df.plot(title = title, fontsize = 12)
	ax.set_xlabel('Date')
	ax.set_ylabel('Price')
	plt.show()

def get_daily_returns(df):
	daily_returns = df.copy()
	# Calculating daily returns
	daily_returns[1:] = (df / df.shift(1)) - 1 
	# Setting daily returns for row 0 to 0.
	daily_returns.ix[0, :] = 0
	return daily_returns

symbols = ['SPY', 'AAPL']

if __name__ == "__main__":
	dates = dates_creator()
	df = get_data(symbols, dates)
	daily_returns = get_daily_returns(df)
	plot(df, 'Stock prices')
	plot(daily_returns,'Daily returns')
	

	
	

