import pandas as pd
import datetime
from pandas_datareader import data
from tqdm import tqdm

tickers = ['SPY' , 'IBM', 'GOOG', 'TSLA', 'GLD']

for ticker in tqdm(tickers):
    end_date = datetime.datetime.now()
    start_date = end_date - datetime.timedelta(days = 365 * 5.0)
    df = data.get_data_yahoo(ticker, start = start_date, end = end_date, interval='d')
    df.to_csv("{}.csv".format(ticker))