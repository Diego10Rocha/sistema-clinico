package interfaces;

import java.util.List;

import model.Usuario;

public interface IFindUser {
	
	public abstract Object findUser(String keyWord);
	public abstract Object findUser(String keyWord1, String keyWord2);
	public abstract <T extends Usuario> List<T> readFile(Class<T[]> type );
}
