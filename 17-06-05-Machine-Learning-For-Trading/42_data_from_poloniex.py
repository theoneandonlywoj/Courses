from poloniex import Poloniex

connection = Poloniex()
BTC_ETH = connection.returnTicker()#['BTC_ETH']

print(type(BTC_ETH))
print(BTC_ETH.keys())
#print(BTC_ETH['high24hr'])