package com.chihchencheng.stock.api;

import com.chihchencheng.stock.model.Stock;
import com.chihchencheng.stock.service.StockService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/")
@RestController
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("addStock")
    public void addStock(@Validated @NotNull @RequestBody Stock stock){
        stockService.addStock(stock);
    }

    @GetMapping("getAllStocks")
    public List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    @GetMapping(path = "{id}")
    public Stock getStockById(@PathVariable("id") UUID id){
        return stockService.getStockByID(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStockById(@PathVariable("id") UUID id){
        stockService.deleteStock(id);
    }

    @PutMapping(path = "{id}")
    public void updateStock(@PathVariable("id") UUID id, @Validated @NotNull @RequestBody Stock stockToUpdate){
        stockService.updateStock(id, stockToUpdate);
    }
}
