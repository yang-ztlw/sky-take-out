package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "C端-地址簿接口")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     * @return
     */
    @PostMapping
    @ApiOperation("新增地址")
    public Result<String> add(@RequestBody AddressBook addressBook){
        addressBookService.add(addressBook);
        return Result.success();
    }

    /**
     * 查询当前登陆用户的所有地址信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询当前登陆用户的所有地址信息")
    public Result<List<AddressBook>> list(){
        List<AddressBook> list = addressBookService.list();
        return Result.success(list);
    }

    /**
     * 查询默认地址
     * @return
     */
    @ApiOperation("查询默认地址")
    @GetMapping("/default")
    public Result<AddressBook> selectDefault(){
        AddressBook addressBook = addressBookService.selectDefault();
        return Result.success(addressBook);
    }

    /**
     * 根据id修改地址
     * @param addressBook
     * @return
     */
    @PutMapping
    @ApiOperation("根据id修改地址")
    public Result<String> update(@RequestBody AddressBook addressBook){
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 根据id删除地址
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据id删除地址")
    public Result<String> delete(Long id){
        addressBookService.delete(id);
        return Result.success();
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> getById(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result<String> setDefault(@RequestBody AddressBook addressBook){
        addressBookService.setDefault(addressBook.getId());
        return Result.success();
    }
}
