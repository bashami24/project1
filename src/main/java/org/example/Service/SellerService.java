package org.example.Service;

import org.example.Model.Seller;
import org.example.exception.SellerAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SellerService {

    private final List<Seller> sellerList;

    public SellerService() {
        this.sellerList = new ArrayList<>();
    }
public List<Seller> getAllSellers(){
        return sellerList;

}
    public void addSeller(Seller seller) throws SellerAlreadyExistsException {
        if (isSellerNameExists(seller.getName()))  {
            throw new SellerAlreadyExistsException("Seller With name '" + seller.getName() + "'Already Exists.");
        }
        sellerList.add(seller);
        }

        private boolean isSellerNameExists(String name){
        return sellerList.stream().anyMatch(s ->s.getName().equals(name));
    }

}