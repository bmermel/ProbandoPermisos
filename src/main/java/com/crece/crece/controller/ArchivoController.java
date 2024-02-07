package com.crece.crece.controller;

import com.crece.crece.model.Archivo;
import com.crece.crece.model.MailStructure;
import com.crece.crece.model.Usuario;
import com.crece.crece.model.dto.ArchivoDTO;
import com.crece.crece.model.dto.GetEdificioListDto;
import com.crece.crece.model.dto.GetUsuarioDTO;
import com.crece.crece.service.ArchivoService;
import com.crece.crece.service.EdificioService;
import com.crece.crece.service.MailService;
import com.crece.crece.service.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class ArchivoController {


    @Autowired
    private ArchivoService service;

    @Autowired
    private MailService mailService;
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "*")
    @PostMapping("/fileSystem/{edificioId}/{categoria}/{destinatario}/{alias}")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file,
                                                     @PathVariable Long edificioId,
                                                     @PathVariable String categoria,
                                                     @PathVariable String destinatario,
                                                     @PathVariable String alias) throws IOException {
        String uploadedFile = service.uploadImageToFileSystem(file, edificioId, categoria, destinatario,alias);

        // Crear un objeto MailStructure
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject(categoria + ": Nueva Comunicación");
        mailStructure.setMessage("Mensaje del correo");

        // Supongamos también que tienes una lista de destinatarios (mails)
        List<String> mails = usuarioService.getEmailsPorEdificio(edificioId, destinatario);

        // Ahora puedes llamar al método sendMailAttach de mailService de forma asíncrona
        CompletableFuture.runAsync(() -> sendMailAsync(mails, mailStructure, uploadedFile));

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadedFile);
    }

    @Async
    private CompletableFuture<Void> sendMailAsync(List<String> mails, MailStructure mailStructure, String uploadedFile) {
        try {
            mailService.sendMailAttach(mails, mailStructure, uploadedFile);
            System.out.println("Correo enviado después de cargar el archivo.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            // Manejar la excepción si es necesario
        }
        return null;
    }
    @GetMapping("/fileSystem/uploadedFiles/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) {
        try {
            byte[] imageData = service.downloadImageFromFileSystem(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Archivo no encontrado: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud");
        }
    }

    @GetMapping("/fileSystem/all")
    public ResponseEntity<?> getAllFiles() {
        List<ArchivoDTO> archivos = service.getAllArchivos();  // Necesitas implementar este método en ArchivoService
        return ResponseEntity.status(HttpStatus.OK)
                .body(archivos);
    }

    @DeleteMapping("/fileSystem/delete/{id}")
    public ResponseEntity<?> borrarArchivo(@PathVariable Long id){
        service.borrarArchivo(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Archivo borrado");
    }

    @GetMapping("/fileSystem/archivosPorEdificio/{edificioId}")
    public ResponseEntity<?> getArchivosPorEdificio(@PathVariable Long edificioId) {
        List<ArchivoDTO> archivos = service.getArchivosPorEdificio(edificioId);
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @GetMapping("/fileSystem/archivosPorCategoria/{categoria}")
    public ResponseEntity<?> getArchivosPorCategoria(@PathVariable String categoria) {
        List<ArchivoDTO> archivos = service.getArchivosPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @GetMapping("/fileSystem/archivosPorNombre/{nombre}")
    public ResponseEntity<?> getArchivosPorNombre(@PathVariable String nombre) {
        List<ArchivoDTO> archivos = service.getArchivosPorNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @GetMapping("/fileSystem/archivosOrdenadosPorFecha")
    public ResponseEntity<?> getArchivosOrdenadosPorFecha() {
        List<ArchivoDTO> archivos = service.getArchivosOrdenadosPorFecha();
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

}
