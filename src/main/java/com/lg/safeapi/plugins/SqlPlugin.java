package com.lg.safeapi.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.logging.jdbc.StatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Proxy;
import java.sql.Statement;

/**
 * 获取不带占位符的可直接执行sql。
 * @author amazfit
 * @date 2022-07-09 下午1:04
 **/
//@Slf4j
@Intercepts({@Signature(type = StatementHandler.class,method = "parameterize",args={Statement.class})})
public class SqlPlugin implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(SqlPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //此处即org.apache.ibatis.executor.statement.StatementHandler.parameterize
        Object returnObject = invocation.proceed();
        Statement statement = (Statement) invocation.getArgs()[0];
        String finalExecutableSql = null;
        if (Proxy.isProxyClass(statement.getClass())){
            MetaObject metaObject = SystemMetaObject.forObject(statement);
            /*h的可能值 jdk代理 InvocationHandler
            * PreparedStatementLogger
            * StatementLogger
            * */
            Object h = metaObject.getValue("h");
            if(h instanceof StatementLogger){
                RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
                finalExecutableSql = routingStatementHandler.getBoundSql().getSql();
            }else{
                PreparedStatementLogger psl = (PreparedStatementLogger) h;
                finalExecutableSql = psl.getPreparedStatement().toString();
            }
        }else{
            finalExecutableSql = statement.toString();
        }
        log.info("最终不带占位符的可直接执行sql：{}",finalExecutableSql);
        return returnObject;
    }
}
