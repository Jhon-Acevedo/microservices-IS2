package uptc.edu.rolPermission.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.infrastructure.repository.mapper.PermissionMapper;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;
import uptc.edu.rolPermission.infrastructure.repository.dto.RolPermissionDto;
import uptc.edu.rolPermission.infrastructure.repository.mapper.RolPermissionMapper;

import java.util.List;
import java.util.Optional;

@Component
public class RolPermissionRepositoryImpl implements RolPermissionRepository {

    private final RolPermissionRepositoryMongo rolPermissionRepositoryMongo;

    public RolPermissionRepositoryImpl(RolPermissionRepositoryMongo rolPermissionRepositoryMongo) {
        this.rolPermissionRepositoryMongo = rolPermissionRepositoryMongo;
    }

    @Override
    public List<RolPermission> findAll() {
        List<RolPermissionDto> rolPermissionDtoList = rolPermissionRepositoryMongo.findAll();
        return RolPermissionMapper.toDomain(rolPermissionDtoList);
    }

    @Override
    public Optional<RolPermission> findById(String id) {
        Optional<RolPermissionDto> rolPermissionDto = rolPermissionRepositoryMongo.findById(id);
        return rolPermissionDto.map(RolPermissionMapper::toDomain);
    }

    @Override
    public RolPermission create(Rol rol, Permission permission) {
        return RolPermissionMapper.toDomain(
                rolPermissionRepositoryMongo.save(RolPermissionMapper.toDto(new RolPermission(null, rol, permission)))
        );
    }

    @Override
    public boolean delete(String id) {
        return findById(id)
                .map(rolPermission -> {
                    rolPermissionRepositoryMongo.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public RolPermission getRolPermissionByRolAndPermission(String idRol, String idPermission) {
        return RolPermissionMapper.toDomain(
                rolPermissionRepositoryMongo.getRolPermissionByRolAndPermission(idRol, idPermission)
        );
    }

    @Override
    public List<RolPermission> findByRol(String idRol) {
        return null;
    }

    @Override
    public Optional<RolPermission> update(int id, int idRol, int idPermission) {
        return Optional.empty();
    }
}
