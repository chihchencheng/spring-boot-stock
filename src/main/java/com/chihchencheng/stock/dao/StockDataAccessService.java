package com.chihchencheng.stock.dao;

import com.chihchencheng.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class StockDataAccessService implements StockDao{

    private final JdbcTemplate jdbcTemplate;

    private static List<Stock> DB = new ArrayList<>();

    @Autowired
    public StockDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertStock(UUID id, Stock stock) {
        DB.add(new Stock(id, stock.getName()));
        return 1;
    }

    @Override
    public List<Stock> selectAllStocks() {
        final String sql = "SELECT id, name FROM stocks";
        return jdbcTemplate.query(sql, (resultSet, i)->{
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Stock(id,name);
        });
    }

    @Override
    public Optional<Stock> selectStockByID(UUID id) {
        final String sql = "SELECT id, name From stock WHERE id = ?";
        Stock stock = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i)->{
                    UUID stockId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Stock(stockId,name);
                });
        return Optional.ofNullable(stock);
    }

    @Override
    public int deletStokcById(UUID id) {
        Optional<Stock> stockMaybe = selectStockByID(id);
        if (!stockMaybe.isPresent()) {
            return 0;
        }
        DB.remove(stockMaybe.get());
        return 1;
    }

    @Override
    public int updateStockByID(UUID id, Stock update) {
        return selectStockByID(id)
                .map(stock -> {
                    int indexOfStockToUpdate = DB.indexOf(stock);
                    if(indexOfStockToUpdate >= 0) {
                        DB.set(indexOfStockToUpdate, new Stock(id, update.getName()));
                        return 1;
                    }
                    return  0;
                })
                .orElse(0);
    }


}
