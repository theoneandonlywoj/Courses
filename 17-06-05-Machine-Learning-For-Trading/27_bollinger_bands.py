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

def plot(df, symbols):
	ax = df.plot(title = 'Stock prices', fontsize = 12)
	ax.set_xlabel('Date')
	ax.set_ylabel('Price')
	plt.show()

def get_rolling_mean(df, window):
	return  df.rolling(window = window, center = False).mean()

def get_rolling_std(df, window):
	return  df.rolling(window = window, center = False).std()

def bollinger_bands(df, window):
	rolling_mean = get_rolling_mean(df, window)
	rolling_std = get_rolling_std(df, window)

	upper_band = rolling_mean + 2 * rolling_std
	lower_band = rolling_mean - 2 * rolling_std

	return upper_band, lower_band



def print_pred_statistics(df, window):
	# Plotting SPY
	ax = df['SPY'].plot(title = 'SPY vs SPY Rolling Mean', label = 'SPY')
	# Updated API for rolling mean!
	rm_SPY = get_rolling_mean(df['SPY'], window) 
	# Plotting Rolling Mean of SPY
	rm_SPY.plot(label = 'Rolling Mean', ax = ax )
	# Calculating Bollinger Bands (R)
	upper_bollinger, lower_bollinger = bollinger_bands(df['SPY'], window = window)
	upper_bollinger.plot(label = 'Upper band', ax = ax)
	lower_bollinger.plot(label = 'Lower band', ax = ax)
	# Adding the legend
	ax.legend(loc = 'upper left')
	# Show!
	plt.show()

symbols = ['SPY']

if __name__ == "__main__":
	dates = dates_creator()
	df = get_data(symbols, dates)
	print_pred_statistics(df, window = 20)
	

	
	

