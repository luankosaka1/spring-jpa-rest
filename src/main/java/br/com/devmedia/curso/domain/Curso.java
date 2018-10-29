package br.com.devmedia.curso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(
        name = "cursos",
        indexes = {
                @Index(
                        columnList = "titulo, data_inicio",
                        unique = true,
                        name = "unique_titulo_dataInicio"
                )
        }
)
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "carga_horaria", nullable = false)
    @Enumerated(EnumType.STRING)
    private CargaHoraria cargaHoraria;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_inicio")
    private Date dataInicio;

    public Curso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CargaHoraria getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(CargaHoraria cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id) &&
                Objects.equals(titulo, curso.titulo) &&
                cargaHoraria == curso.cargaHoraria &&
                Objects.equals(dataInicio, curso.dataInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, cargaHoraria, dataInicio);
    }
}
