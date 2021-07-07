    package com.example.springdemo.dal.fsriskmanagement.mapper;
    import org.apache.ibatis.annotations.Param;
        import com.example.springdemo.dal.fsriskmanagement.dataobject.ManagerDO;
    /**
    *
    * The Table FK_MANAGER.
    * 风控中心管理员表
    */
    public interface ManagerDOMapper{

        /**
        * desc:insert:FK_MANAGER.<br/>
        * descSql =  INSERT INTO FK_MANAGER VALUES 
            * @param entity entity
        * @return int
        */
        int insert(ManagerDO entity);
        /**
        * desc:根据用户名称查询管理员信息.<br/>
        * descSql =  SELECT * FROM FK_MANAGER WHERE username = #{username, jdbcType=VARCHAR} LIMIT 1
            * @param username username
        * @return ManagerDO
        */
        ManagerDO getByUsername(String username);
        /**
        * desc:根据id更新管理员TOKEN信息.<br/>
        * descSql =  UPDATE FK_MANAGER SET TOKEN = #{token, jdbcType=VARCHAR} WHERE id = #{id, jdbcType=INTEGER}
            * @param entity entity
        * @return int
        */
        int updateById(ManagerDO entity);
    }
