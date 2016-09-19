/*
 * Copyright (C) 2016 kleberkruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.desafio.model.dao;

/**
 *
 * @author kleberkruger
 */
public class DAOFactory {

    private final AlunoDAO alunoDAO;
    private final EnderecoDAO enderecoDAO;
    private final EscolaDAO escolaDAO;
    private final MunicipioDAO municipioDAO;
    private final ProfessorDAO professorDAO;
    private final ResponsavelDAO responsavelDAO;
    private final SerieDAO serieDAO;
    private final TelefoneDAO telefoneDAO;
    private final TurmaDAO turmaDAO;

    private DAOFactory() {
        this.alunoDAO = new AlunoDAO();
        this.enderecoDAO = new EnderecoDAO();
        this.escolaDAO = new EscolaDAO();
        this.municipioDAO = new MunicipioDAO();
        this.professorDAO = new ProfessorDAO();
        this.responsavelDAO = new ResponsavelDAO();
        this.serieDAO = new SerieDAO();
        this.telefoneDAO = new TelefoneDAO();
        this.turmaDAO = new TurmaDAO();
    }

    /**
     * @return the alunoDAO
     */
    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }

    /**
     * @return the enderecoDAO
     */
    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    /**
     * @return the escolaDAO
     */
    public EscolaDAO getEscolaDAO() {
        return escolaDAO;
    }

    /**
     * @return the municipioDAO
     */
    public MunicipioDAO getMunicipioDAO() {
        return municipioDAO;
    }

    /**
     * @return the professorDAO
     */
    public ProfessorDAO getProfessorDAO() {
        return professorDAO;
    }

    /**
     * @return the responsavelDAO
     */
    public ResponsavelDAO getResponsavelDAO() {
        return responsavelDAO;
    }
    
    /**
     * @return the serieDAO
     */
    public SerieDAO getSerieDAO() {
        return serieDAO;
    }

    /**
     * @return the telefoneDAO
     */
    public TelefoneDAO getTelefoneDAO() {
        return telefoneDAO;
    }

    /**
     * @return the turmaDAO
     */
    public TurmaDAO getTurmaDAO() {
        return turmaDAO;
    }

    /**
     * Gets the single instance of DAOFactory class.
     *
     * @return the singleton instance
     */
    public static DAOFactory getInstance() {
        return DAOFactoryHolder.INSTANCE;
    }

    /**
     * Classe privada que armazena a única instância de DAOFactory.
     */
    private static class DAOFactoryHolder {

        private static final DAOFactory INSTANCE = new DAOFactory();
    }

}
