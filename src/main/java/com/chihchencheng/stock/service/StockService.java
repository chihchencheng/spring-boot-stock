package com.chihchencheng.stock.service;

import com.chihchencheng.stock.dao.StockDao;
import com.chihchencheng.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService {

    private final StockDao stockDao;

    @Autowired
    public StockService(@Qualifier("postgres") StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public int addStock(Stock stock){
        return stockDao.insertStock(stock);
    }

    public List<Stock> getAllStocks(){
        return stockDao.selectAllStocks();
    }

    public Optional<Stock> getStockByID(UUID id) {
        return stockDao.selectStockByID(id);
    }

    public int deleteStock(UUID id){
        return stockDao.deletStokcById(id);
    }

    public int updateStock(UUID id, Stock newStock){
        return stockDao.updateStockByID(id, newStock);
    }
}
