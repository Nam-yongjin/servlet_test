package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// 트랜잭션 전파(보급)에서 트랜잭션이 있으면 해당 트랜잭션 사용하고, 트랜잭션이 없으면 새로운 트랜잭션 생성한다.
// 주석처리 후 문법오류 발생시킨뒤 트랜젝션 하지 않으면 출금만되고 입금은 안되는 오류
// 문법오류 발생시킨 상태로 트랜잭션을 주석을 풀면 Rollback 처리 되면서 출금하려다 출금이 되지 않는 상태로 돌아감
@Transactional(propagation=Propagation.REQUIRED)
public class AccountService {
	private AccountDAO accDAO;
	
	public void setAccDAO(AccountDAO accDAO) {
		 this.accDAO = accDAO;
	}
	
	public void sendMoney() throws Exception{
		accDAO.updateBalance1();
		accDAO.updateBalance2();
	}
}
