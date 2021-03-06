/**
 * Created by Apache CXF WadlToJava code generator
**/
package ru.ilb.jparestresource.api;

import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ru.ilb.jparestresource.model.Document;

/**
 * documents resource
 */
@Path("documents")
public interface DocumentsResource {

    /**
     * List of documents
     */
    @GET
    @Produces({"application/xml", "application/json" })
    @Valid
    List<Document> list(@QueryParam("limit") Integer limit, @QueryParam("order") String order);

    /**
     * create new document instance
     */
    @POST
    @Consumes({"application/xml", "application/json" })
    @Produces("text/plain")
    long create(@Valid Document document);

    /**
     * get document instance
     * @param documentId document id
     */
    @GET
    @Produces({"application/xml", "application/json" })
    @Valid
    @Path("/{documentId}")
    Document find(@PathParam("documentId") long documentId);

    /**
     * edit document instance
     * @param documentId document id
     */
    @PUT
    @Consumes({"application/xml", "application/json" })
    @Path("/{documentId}")
    void edit(@PathParam("documentId") long documentId, @Valid Document document);

    /**
     * remove document instance
     * @param documentId document id
     */
    @DELETE
    @Path("/{documentId}")
    void remove(@PathParam("documentId") long documentId);

}