package br.com.petshow.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshow.exceptions.ExceptionNotFoundRecord;


@Transactional
public  abstract class SuperClassDAO<T>  {
	private final String MSG_SUCESS_SAVE = "Registro salvo com sucesso!";
	private final String MSG_SUCESS_DEL  = "Registro excluido!";
	private final String MSG_SUCESS_UPD  = "Registro atualizado com sucesso!";
	private final String MSG_ERRO_SAVE	 = "Erro ao tentar salvar o registro.";
	private final String MSG_ERRO_DEL	 = "Erro ao tentar excluir o registro.";
	private final String MSG_NOT_FOUND	 = "Registro não encontrado.";
	
	public abstract T find(long codigo) ;
	
	@PersistenceContext
	protected EntityManager manager;
	
	private Session	session;
	
	public SuperClassDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMSG_NOT_FOUND() {
		return MSG_NOT_FOUND;
	}
	
	public String getMSG_SUCESS_SAVE() {
		return MSG_SUCESS_SAVE;
	}
	public String getMSG_SUCESS_DEL() {
		return MSG_SUCESS_DEL;
	}
	public String getMSG_SUCESS_UPD() {
		return MSG_SUCESS_UPD;
	}
	public String getMSG_ERRO_SAVE() {
		return MSG_ERRO_SAVE;
	}
	public String getMSG_ERRO_DEL() {
		return MSG_ERRO_DEL;
	}
	public Session getSession() {
		return session;
	}

	public T insert(T entidade){
		manager.persist(entidade);
		return entidade;
			
	}

	
	public void delete(long codigo) throws ExceptionNotFoundRecord{
	
			
			T entidade = this.find(codigo);
			if(entidade != null){
				manager.remove(entidade);
			}else {
				throw new ExceptionNotFoundRecord();
			}

		
	}

	public void delete(T entidade) {
		manager.remove(manager.merge(entidade));
		
	}

	public T update(T entidade){
		
		manager.merge(entidade);
		return entidade;
	}

	


	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}


}
