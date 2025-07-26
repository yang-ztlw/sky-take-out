package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     * @param addressBook
     */
    @Override
    public void add(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    /**
     * 查询当前登陆用户的所有地址信息
     * @return
     */
    @Override
    public List<AddressBook> list() {
        return addressBookMapper.list(BaseContext.getCurrentId());
    }

    /**
     * 查询默认地址
     * @return
     */
    @Override
    public AddressBook selectDefault() {
        return addressBookMapper.selectDefault(BaseContext.getCurrentId());
    }

    /**
     * 根据id修改地址
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id删除地址
     * @param id
     */
    @Override
    public void delete(Long id) {
        addressBookMapper.delete(id);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    @Override
    public void setDefault(Long  id) {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());

        //1.将当前用户的所有地址都设置为非默认地址
        addressBook.setIsDefault(0);
        addressBookMapper.update(addressBook);

        //2.将指定id的地址设置为默认地址
        addressBook.setId((id));
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);
    }
}
