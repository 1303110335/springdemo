    package com.example.springdemo.dal.fsriskmanagement.dao;

    import com.example.springdemo.dal.fsriskmanagement.dataobject.ManagerDO;
    import com.example.springdemo.dal.fsriskmanagement.mapper.ManagerDOMapper;
    import org.springframework.beans.factory.annotation.Autowired;

    /**
    * The Table FK_MANAGER.
    * 风控中心管理员表
    */
//    @Repository
    public class ManagerDAO{

        @Autowired
        private ManagerDOMapper managerDOMapper;

        /**
        * desc:insert:FK_MANAGER.<br/>
        * descSql =  INSERT INTO FK_MANAGER VALUES 
            * @param entity entity
        * @return int
        */
        public int insert(ManagerDO entity){
            return managerDOMapper.insert(entity);
        }
        /**
        * desc:根据用户名称查询管理员信息.<br/>
        * descSql =  SELECT * FROM FK_MANAGER WHERE username = #{username, jdbcType=VARCHAR} LIMIT 1
            * @param username username
        * @return ManagerDO
        */
        public ManagerDO getByUsername(String username){
            return managerDOMapper.getByUsername(username);
        }
        /**
        * desc:根据id更新管理员TOKEN信息.<br/>
        * descSql =  UPDATE FK_MANAGER SET TOKEN = #{token, jdbcType=VARCHAR} WHERE id = #{id, jdbcType=INTEGER}
            * @param entity entity
        * @return int
        */
        public int updateById(ManagerDO entity){
            return managerDOMapper.updateById(entity);
        }
    }
