import os
import pandas as pd 
import matplotlib.pyplot as plt 

''' Read: http://pandas.pydata.org/pandas-docs/stable/api.html#api-dataframe-stats '''

def symbol_to_path(symbol, base_dir = 'data'):
    return os.path.join(base_dir, "{}.csv".format(str(symbol)))

def dates_creator():
    start_date = '2009-01-01'
    end_date = '2015-12-31'
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

def plot(df):
    ax = df.plot(title = 'Stock prices', fontsize = 12)
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
    
def print_mean(df):
    print('Mean:')
    print(df.mean())
    
def print_std(df):
    print('Mean:')
    print(df.std())
    
def print_kurtosis(df):
    print('Kurtosis:')
    print(df.kurtosis())
 

symbols = ['SPY', 'IBM']

if __name__ == "__main__":
    dates = dates_creator()
    df = get_data(symbols, dates)
    daily_returns = get_daily_returns(df)
    plot(df)
    plot(daily_returns)
    # Showing histogram of the daily returns
    daily_returns['IBM'].hist(bins = 100, label = 'IBM')
    daily_returns['SPY'].hist(bins = 100, label = 'SPY')
    
    plt.legend(loc = 'upper right')
    plt.show()
    
    # Printing mean of the daily returns
    print_mean(daily_returns)
    # Printing standard deviation of the daily returns
    print_std(daily_returns)
    # Printing kurtosis of the daily returns
    print_kurtosis(daily_returns)
    
    
    
    
    

    
    

