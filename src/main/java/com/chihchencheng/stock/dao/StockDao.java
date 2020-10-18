package com.chihchencheng.stock.dao;

import com.chihchencheng.stock.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockDao {
    int insertStock(UUID id, Stock stock);
    default int insertStock(Stock stock){
        UUID id = UUID.randomUUID();
        return insertStock(id, stock);
    }
    List<Stock> selectAllStocks();

    Optional<Stock> selectStockByID(UUID id);

    int deletStokcById(UUID id);

    int updateStockByID(UUID id, Stock stock);
}
