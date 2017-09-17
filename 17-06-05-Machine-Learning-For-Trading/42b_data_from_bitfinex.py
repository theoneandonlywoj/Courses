from bitfinex.client import Client
from tqdm import tqdm

client = Client()

symbols = client.symbols()
print(symbols)

'''
for symbol in symbols:
    print('Data for:' + symbol)
    print('Ask:' + str(client.ticker(symbol)['ask']))
    print('Bid:' + str(client.ticker(symbol)['bid']))
    print('Mid:' + str(client.ticker(symbol)['mid']))
'''  
''' 
for symbol in symbols:
#symbol = 'btcusd'
    print(symbol)
    print(client.today(symbol))
    #print(client.stats(symbol))
    

    print(client.lendbook('btc', parameters))
'''
'''
parameters = {'limit_asks': 2, 'limit_bids': 2}

print(client.lendbook('btc', parameters))
print(client.order_book(symbol, parameters))
'''
#parameters = {'limit_asks': 2, 'limit_bids': 2}
#print(client.lendbook('btc', parameters))


    