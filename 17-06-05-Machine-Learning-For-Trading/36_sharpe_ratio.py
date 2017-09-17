import os
import numpy as np
import pandas as pd 
import matplotlib.pyplot as plt 

''' Read: http://pandas.pydata.org/pandas-docs/stable/api.html#api-dataframe-stats '''

def symbol_to_path(symbol, base_dir = 'data'):
	return os.path.join(base_dir, "{}.csv".format(str(symbol)))

def dates_creator():
	start_date = '2012-01-01'
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
	return df

def normalize_data(df):
	return df / df.ix[0,:]

def plot(df, title):
	ax = df.plot(title = title, fontsize = 12)
	ax.set_xlabel('Date')
	ax.set_ylabel(title)
	plt.show()

def get_daily_returns(df):
	daily_returns = df.copy()
	# Calculating daily returns
	daily_returns[1:] = (df / df.shift(1)) - 1 
	# Setting daily returns for row 0 to 0.
	daily_returns.ix[0, :] = 0
	return daily_returns
     
def annualised_sharpe(returns, N=252):
    """
    Calculate the annualised Sharpe ratio of a returns stream 
    based on a number of trading periods, N. N defaults to 252,
    which then assumes a stream of daily returns.
    
    The function assumes that the returns are the excess of 
    those compared to a benchmark.
    """
    return np.sqrt(N) * returns.mean() / returns.std()

if __name__ == "__main__":
    '''
    *********************************************************************************************
    Sharpe ratio = (Rp - Rf) / Stdev
    in other words: 
    SR = mean(daily-returns - daily-risk-free-rate) / std((daily-returns - daily-risk-free-rate))
    There are few ways to get value of the daily risk free rate:
    - LIBOR
    - 3 months treasury bill
    - 0 %

    We can calculate it in more traditional (easy and fast) way:
    daily_risk_free_rate = [(1 + BANK_RETURN)^(-252)] - 1

    Sharpe ratio can widely depend on the sampling window.
    Originally it was meant to be calculated annually.

    With that, if we calculate it daily, weekly or monthly we need to adjust it.
    SR_annualized = K * SR
    where:
    K = sqrt(sample_per_year)
    - K for daily calcuations will be equal to sqrt(252)
    - K for weekly calcuations will be equal to sqrt(52)
    - K for weekly calcuations will be equal to sqrt(12).


    A Sharp Ratio>1 is considered good, >2 or 3 is what hedge funds look for. 
    Anything too high (>3 or 4) is suspicious.
    *********************************************************************************************
    '''
    symbols = ['SPY', 'AAPL', 'GOOG', 'IBM', 'TSLA']
    dates = dates_creator()
    df = get_data(symbols, dates)
    daily_returns = get_daily_returns(df)
    # Sharpe ratio
    annualized_sharpe_ratio = annualised_sharpe(daily_returns, N=252)
    print('Annualized Sharpe ratio:')
    print(annualized_sharpe_ratio)