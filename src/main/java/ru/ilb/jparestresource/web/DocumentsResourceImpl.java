/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ilb.jparestresource.web;

import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.jparestresource.api.DocumentsResource;
import ru.ilb.jparestresource.utils.JaxbHelper;
import ru.ilb.jparestresource.repositories.DocumentRepository;
import ru.ilb.jparestresource.model.Document;

@Path("documents")
@Api("documents")
public class DocumentsResourceImpl implements DocumentsResource {

    @Autowired
    JaxbHelper jaxbHelper;

    private UriInfo uriInfo;

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Autowired
    DocumentRepository documentRepository;

    private SearchContext searchContext;

    @Context
    public void setSearchContext(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    private static final Logger LOG = LoggerFactory.getLogger(DocumentsResourceImpl.class);

    @Override
    @Transactional
    public Document find(long  documentId) {
        return documentRepository.findOne(documentId);
    }

    @Override
    @Transactional
    public long create(Document document) {
        return documentRepository.save(document).getId();
    }

    @Override
    @Transactional
    public void edit(long  documentId, Document document) {
        Document doc = documentRepository.findOne(documentId);
        BeanUtils.copyProperties(document, doc, new String[]{"id"});
    }

    @Override
    @Transactional
    public void remove(long  documentId) {
        documentRepository.delete(documentId);
    }

    /**
     * Example data initialisation
     */
    @PostConstruct
    @Transactional
    public void init() {
//        Documents documents = jaxbHelper.unmarshal(getClass().getResourceAsStream("/META-INF/xml/testdata.xml"), Documents.class,MediaType.APPLICATION_XML);
//        documentRepository.save(documents.getDocuments());

    }

    @Override
    public List<Document> list(Integer limit, String order) {
        return documentRepository.findAll();
    }

}
