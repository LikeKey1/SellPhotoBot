package ru.likekey.main.dao;

import ru.likekey.main.entity.User;

public interface UserDAO {
    public void saveUser(User user);
    public void updateUser(User user);
    public User getUser(int vkId);
    public void saveBillId(int id, String billId);
    public String getBillId(int id);
    public void deleteBillId(int vkId);
    public void addBalanceToUser(int vkId, int amount);
    public void checkUserInDB(int vkId);
}
