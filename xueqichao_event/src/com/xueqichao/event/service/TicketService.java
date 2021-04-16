package com.xueqichao.event.service;

import com.xueqichao.event.dao.TicketDao;
import com.xueqichao.event.entity.Ticket;

import java.util.Vector;

public class TicketService
{
    TicketDao ticketDao = new TicketDao();
    public Vector<Vector> lookTicket(){
        return ticketDao.lookTicketMysql();
    }

    public void keepTicketMysql(String time,int number,int sid,int price){
        ticketDao.keepTicketMysql(time,number,sid,price);
    }

    public int deleteTicketMysql(int tid){
        return ticketDao.deleteTicketMysql(tid);
    }

    public Ticket getInstance(int tid){
        return ticketDao.getInstanceMysql(tid);
    }

    public int upDateTicket(int tid,String time,int number,int price){
        return ticketDao.updateTicketMysql(tid,time,number,price);
    }

    public Ticket getInstanceMysql(int sid,String time){
        return ticketDao.getInstanceMysql(sid,time);
    }

    public Vector<Vector> lookTicket(String information){
        return ticketDao.lookTicketMysql(information);
    }

    public int updateTicketNum(int tid,int number){
        return ticketDao.updateTicketMysql(tid,number);
    }


}
