import pandas as pd 
import matplotlib.pyplot as plt 

def import_data(file, print_head = False):
	df = pd.read_csv('data/{}.csv'.format(file))
	print(file)
	if print_head == True:
		print("First few values:")
		print(df.head())
	return df

def import_data_date_index(file, print_head = False):
	df = pd.read_csv('data/{}.csv'.format(file), index_col = "Date", parse_dates = True)
	print(file)
	if print_head == True:
		print("First few values:")
		print(df.head())
	return df

def import_data_date_index_one_col(file, print_head = False):
	df = pd.read_csv('data/{}.csv'.format(file), 
		index_col = 'Date', 
		parse_dates = True, 
		usecols = ['Date', 'Adj Close'],
		na_values = ['nan']
		)
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

def plot_high_price(df):
	df['High'].plot()
	plt.show()
def plot_two_columns(df):
	df[['High', 'Adj Close']].plot()
	plt.show()

def creating_empty_df():
	start_date = '2010-01-22'
	end_date = '2010-01-26'
	dates = pd.date_range(start_date, end_date) # datetime index object / 
	# Creating an empty DataFrame
	# if not using index = the indexes will be from zero to ...
	df1 = pd.DataFrame(index = dates) 
	return df1

def join_dataframes(df_1, df_2):
	df_1 = df_1.join(df_2)
	return df_1

def drop_nan(df):
	df = df.dropna() # Dropping NaN values
	return df

if __name__ == '__main__':
	for company in ["SPY"]:
		df = import_data_date_index_one_col(company)
		df1 = creating_empty_df()
		two_df = join_dataframes(df1, df)
		two_df = drop_nan(two_df)
		print (two_df)
print("DONE!")