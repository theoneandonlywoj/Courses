import os
import pandas as pd 

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

symbols = ['AAPL', 'SPY' , 'IBM', 'GOOG', 'TSLA']

def get_mean_columns(dataframe):
	mean_column = dataframe.mean()
	print('*' * 50)
	print('Mean values:')
	print(mean_column) # 1-D row-wise array (1 row with mean values)
	return mean_column

def get_median_columns(dataframe):
	median_column = dataframe.median()
	print('*' * 50)
	print('Median values:')
	print(median_column) 
	return median_column

def get_std_columns(dataframe):
	std = dataframe.std()
	print('*' * 50)
	print('Standard deviation:')
	print(std) 
	return std

def get_sum(dataframe):
	summarized = dataframe.sum()
	print('*' * 50)
	print('Sum of all rows in the columns:')
	print(summarized) 
	return summarized

def get_prod(dataframe):
	'''
	Return the product of the values for the requested axis.
	'''
	prod = dataframe.prod(skipna = True)
	print('*' * 50)
	print('Prod:')
	print(prod) 
	return prod

def get_mode(dataframe):
	'''
	DataFrame.mode(axis=0, numeric_only=False)[source]
	Gets the mode(s) of each element along the axis selected. 
	Adds a row for each mode per label, fills in gaps with nan.

	Note that there could be multiple values returned for 
	the selected axis (when more than one item share the maximum frequency), 
	which is the reason why a dataframe is returned. If you want to impute 
	missing values with the mode in a dataframe df, you can just do this: df.fillna(df.mode().iloc[0])

	Source: http://pandas.pydata.org/pandas-docs/stable/generated/pandas.DataFrame.mode.html
	'''
	mode = dataframe.mode()
	print('*' * 50)
	print('Mode:')
	print(mode) 
	return mode

if __name__ == "__main__":
	dates = dates_creator()
	df = get_data(symbols, dates)
	mean = get_mean_columns(df)
	median = get_median_columns(df)
	std = get_std_columns(df)
	sum_all_rows = get_sum(df)
	prod = get_prod(df)
	mode = get_mode(df)

