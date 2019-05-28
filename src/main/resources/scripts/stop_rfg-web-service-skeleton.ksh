#!/bin/ksh

APP_USER=`whoami`
APP_PID=`ps -ef | grep java | grep smart-market-api | grep ${APP_USER} | grep -v grep | awk '{print $2}'`

if [ -n $APP_PID ]; then
        kill -9 $APP_PID

fi

