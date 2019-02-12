package com.example.springbootlearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {


    @ModelAttribute
    protected void checkToken(@RequestHeader(value = "token") String token) {
        Assert.hasLength(token, "'token' must be not empty.");
    }

    /**
     * @api {get} /user 获取用户列表
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiHeader {String} token 访问令牌
     * @apiSuccess (HTTP - 200) {List[User]} list 用户列表
     * @apiSuccess (HTTP - 200) {Integer} list.id 用户ID
     * @apiSuccess (HTTP - 200) {String} list.username 用户名
     * @apiSuccess (HTTP - 200) {String} list.fullname 姓名
     * @apiSuccess (HTTP - 200) {Number} list.sex 性别: 1-男性, 0-女性
     * @apiSuccess (HTTP - 200) {Boolean} list.status 状态: true-启用, false-禁用
     * @apiSuccessExample {json} 正常返回示例
     * [{"id":1,"username":"zhangsan","fullname":"张三","sex":1,"status":true},{"id":2,"username":"wangwu","fullname":"王五","sex":1,"status":false}]
     * @apiError (HTTP - 400) ERR-001 错误001
     * @apiError (HTTP - 400) ERR-002 错误002
     * @apiErrorExample {json} 出错返回示例
     * {"error":"ERR-001"}
     */
    @GetMapping(value = "")
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "zhangsan", "张三", 1, true));
        list.add(new User(2, "wangwu", "王五", 1, true));
        return list;
    }

    /**
     * @api {get} /user/{id} 根据用户ID获取用户
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiHeader {String} token 访问令牌
     * @apiParam {Integer} id 用户ID
     * @apiSuccess (HTTP - 200) {Integer} id 用户ID
     * @apiSuccess (HTTP - 200) {String} username 用户名
     * @apiSuccess (HTTP - 200) {String} fullname 姓名
     * @apiSuccess (HTTP - 200) {Number} sex 性别: 1-男性, 0-女性
     * @apiSuccess (HTTP - 200) {Boolean} status 状态: true-启用, false-禁用
     * @apiSuccessExample {json} 正常返回示例
     * {"id":1,"username":"zhangsan","fullname":"张三","sex":1,"status":true}
     * @apiError (HTTP - 400) ERR-001 错误001
     * @apiError (HTTP - 400) ERR-002 错误002
     * @apiErrorExample {json} 出错返回示例
     * {"error":"ERR-001"}
     */
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") int id) {
        return new User(1, "zhangsan", "张三", 1, true);
    }

    /**
     * @api {post} /user 新增用户
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiHeader {String} token 访问令牌
     * @apiParam {String} username 用户名
     * @apiParam {String} fullname 姓名
     * @apiParam {Number} sex = 1 性别: 1-男性, 0-女性
     * @apiParam {Boolean} status = true 状态: true-启用, false-禁用
     * @apiSuccess (HTTP - 200) {Integer} id 用户ID
     * @apiSuccess (HTTP - 200) {String} username 用户名
     * @apiSuccess (HTTP - 200) {String} fullname 姓名
     * @apiSuccess (HTTP - 200) {Number} sex 性别: 1-男性, 0-女性
     * @apiSuccess (HTTP - 200) {Boolean} status 状态: true-启用, false-禁用
     * @apiSuccessExample {json} 正常返回示例
     * {"id":1,"username":"zhangsan","fullname":"张三","sex":1,"status":true}
     * @apiError (HTTP - 400) ERR-001 错误001
     * @apiError (HTTP - 400) ERR-002 错误002
     * @apiErrorExample {json} 出错返回示例
     * {"error":"ERR-001"}
     */
    @PostMapping(value = "")
    public User add(@RequestParam(value = "username") String username, @RequestParam(value = "fullname") String fullname, @RequestParam(value = "sex") int sex, @RequestParam(value = "status") boolean status) {
        return new User(3, username, fullname, sex, status);
    }

    /**
     * @api {put} /user/{id} 更新用户
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiHeader {String} token 访问令牌
     * @apiParam {String} username 用户名
     * @apiParam {String} fullname 姓名
     * @apiParam {Number} sex = 1 性别: 1-男性, 0-女性
     * @apiParam {Boolean} status = true 状态: true-启用, false-禁用
     * @apiSuccess (HTTP - 200) {Boolean} . 结果: true-成功, false-失败
     * @apiSuccessExample {json} 正常返回示例
     * true
     * @apiError (HTTP - 400) ERR-001 错误001
     * @apiError (HTTP - 400) ERR-002 错误002
     * @apiErrorExample {json} 出错返回示例
     * {"error":"ERR-001"}
     */
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") int id, @RequestParam(value = "username") String username, @RequestParam(value = "fullname") String fullname, @RequestParam(value = "sex") int sex, @RequestParam(value = "status") boolean status) {
        return true;
    }

    /**
     * @api {delete} /user/{id} 删除用户
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiHeader {String} token 访问令牌
     * @apiError (HTTP - 400) ERR-001 错误001
     * @apiError (HTTP - 400) ERR-002 错误002
     * @apiErrorExample {json} 出错返回示例
     * {"error":"ERR-001"}
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") int id) {
    }
}
