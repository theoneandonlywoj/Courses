import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from datetime import datetime
import json
from bs4 import BeautifulSoup
import requests
from tqdm import tqdm


def timestamp2date(timestamp):
    # function converts a Uniloc timestamp into Gregorian date
    return datetime.fromtimestamp(int(timestamp)).strftime('%Y-%m-%d')

def date2timestamp(date):
    # function coverts Gregorian date in a given format to timestamp
    return datetime.strptime(date, '%Y-%m-%d').timestamp()

def getCryptoOHLC(fsym, tsym):
    # function fetches a crypto price-series for fsym/tsym and stores
    # it in pandas DataFrame

    cols = ['date', 'timestamp', 'open', 'high', 'low', 'close']
    lst = ['time', 'open', 'high', 'low', 'close']

    timestamp_today = datetime.today().timestamp()
    curr_timestamp = timestamp_today

    for j in range(2):
        df = pd.DataFrame(columns=cols)
        # (limit-1) * 2 = days
        # One year is around 184
        limit = 184 
        url = ("https://min-api.cryptocompare.com/data/histoday?fsym=" + 
            fsym + "&tsym=" + tsym + "&toTs=" + str(int(curr_timestamp)) + "&limit=" + str(limit))
        response = requests.get(url)
        soup = BeautifulSoup(response.content, "html.parser")
        dic = json.loads(soup.prettify())
        for i in range(1, limit):
            tmp = []
            for e in enumerate(lst):
                x = e[0]
                y = dic['Data'][i][e[1]]
                if(x == 0):
                    tmp.append(str(timestamp2date(y)))
                tmp.append(y)
            if(np.sum(tmp[-4::]) > 0):
                df.loc[len(df)] = np.array(tmp)
        df.index = pd.to_datetime(df.date)
        df.drop('date', axis=1, inplace=True)
        curr_timestamp = int(df.iloc[0][0])
        if(j == 0):
            df0 = df.copy()
        else:
            data = pd.concat([df, df0], axis=0)
            # Fixing and error when the dataFrame contained strings instead of floats
            data = data.astype(float)
        
    return data
    
def normalize_data(df):
	return df.divide(df.iloc[0])
 
def get_multiple_cryptos(symbols):
    # Intializing an empty DataFrame
    data = pd.DataFrame()
    
    # Adding columns with data for all requested cryptocurrencies
    for symbol in tqdm(symbols):
        fsym = symbol
        tsym = "BTC"
        data_symbol = getCryptoOHLC(fsym, tsym)
            
        data = pd.concat([data, data_symbol['close']], axis = 1)
        
    # Assinging correct names to the columns
    data.columns = symbols
    return data
    
def find_portfolio_statistics(allocs, df, gen_plot = False):
    dfcopy = df.copy()    
    '''
    Compute portfolio statistics:
    1) Cumulative return
    2) Daily return
    3) Average daily return
    4) Standard deviation of the daily returns
    5) (Annual) Sharpe Ratio
    6) Final value
    7) Total returns
    
    Parameters:
    -----------
    allocs: list of allocation fractions for each stock
            The sum must be equal to 1!
        example: allocs =  [0.0, 0.5, 0.35, 0.15]
    df: DataFrame with the data
    
    Optional:
    ---------
    gen_plot: if True, a plot with performance of the allocation
              compared to SPY500 will be shown.
    '''
    # Normalization
    df = (df / df.iloc[0])
    # Allocation of the resources
    df = df * allocs
    # Sum of the value of the resources
    df = df.sum(axis = 1)
    
    # Compute Portfolio Statistics
    
    # Cumulative return
    cumulative_return = (df.iloc[-1] / df.iloc[0]) - 1
    
    # Daily returns
    dailyreturns = (df.iloc[1:] / df.iloc[:-1].values) - 1
    average_daily_return = dailyreturns.mean(axis = 0)
    yearly_return = average_daily_return #* 252 # 252 days of trading in a year
    
    # Standard deviation of the daily returns
    std_daily_return = dailyreturns.std(axis = 0)
    
    # Sharpe Ratio
    sharpe_ratio = (252 ** (0.5)) * ((average_daily_return - 0) / std_daily_return)
    ending_value = df.iloc[-1]
    total_returns = average_daily_return*(252 / 252)
    if gen_plot == True:
    #Plot portfolio along SPY
        dfcopynormed = dfcopy['SPY'] / dfcopy['SPY'].iloc[0]
        ax = dfcopynormed.plot(title = 'Daily Portfolio Value and SPY', label = 'SPY')
        sumcopy = dfcopy.sum(axis = 1)
        normed = sumcopy/sumcopy.iloc[0]
        normed.plot(label='Portfolio Value', ax = ax)
        ax.set_xlabel('Date')
        ax.set_ylabel('Price')
        ax.legend(loc = 2)
        plt.show()
        
    '''
    print('For allocation as follows:')
    print(allocs)
    print('Mean return:')
    print(mean_return)
    print('Standard deviation:')
    print(std_return)
    print('Annualized Sharpe ratio:')
    print(sharpe_ratio)
    '''
    
    return yearly_return, std_daily_return, sharpe_ratio
    
def generate_random_portfolios(df, num_portfolios, stocks):
    # Number of stocks 
    num_stocks = len(stocks) 
    # Initialization the final result matrix with zeros
    result_matrix = np.zeros([num_portfolios,3])
    for i in tqdm(range(num_portfolios)):
        random = np.random.random(num_stocks)
        allocs = random/ np.sum(random)
        mean_return, std_return, sharpe_ratio = find_portfolio_statistics(allocs, df, gen_plot = False)
        result_matrix[i, 0] = mean_return
        result_matrix[i, 1] = std_return
        result_matrix[i, 2] = sharpe_ratio
    return result_matrix
    
if __name__ == '__main__':
    
    symbols = ['ETH', 'LTC', 'ETC', 'DOGE', 'DGB', 'SC']
    #symbols = ['SC']
    data = get_multiple_cryptos(symbols)
    
    # Normalizing the data
    data = normalize_data(data)
    
    
    #plt.figure(figsize=(12, 4))
    for symbol in symbols:
        plt.plot(data[symbol])
    plt.ylabel('Cyrrency / BTC', fontsize=12)
    plt.legend(loc=2)
    plt.show()
   
    '''
    open_price = data['open']
    high_price = data['high']
    low_price = data['low']
    close_price = data['close']
    '''

