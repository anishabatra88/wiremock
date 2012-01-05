package com.tomakehurst.wiremock.common;

import static com.tomakehurst.wiremock.testsupport.WireMatchers.fileNamed;
import static com.tomakehurst.wiremock.testsupport.WireMatchers.hasExactlyIgnoringOrder;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

public class ServletContextFileSourceTest {
    
    private ServletContextFileSource fileSource;
    
    @Before
    public void init() {
        fileSource = new ServletContextFileSource(new MockServletContext(), "filesource");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void listsTextFilesAtTopLevelIgnoringDirectories() {
        List<TextFile> files = fileSource.listFiles();
        
        assertThat(files, hasExactlyIgnoringOrder(
                fileNamed("one"), fileNamed("two"), fileNamed("three")));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void listsTextFilesRecursively() {
        List<TextFile> files = fileSource.listFilesRecursively();
        
        assertThat(files, hasExactlyIgnoringOrder(
                fileNamed("one"), fileNamed("two"), fileNamed("three"), 
                fileNamed("four"), fileNamed("five"), fileNamed("six"), 
                fileNamed("seven"), fileNamed("eight")));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void throwsUnsupportedExceptionWhenAttemptingToWrite() {
        fileSource.writeTextFile("filename", "filecontents");
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void throwsUnsupportedExceptionWhenAttemptingToCreate() {
        fileSource.createIfNecessary();
    }
    
    
    private static class MockServletContext implements ServletContext {

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public ServletContext getContext(String uripath) {
            return null;
        }

        @Override
        public int getMajorVersion() {
            return 0;
        }

        @Override
        public int getMinorVersion() {
            return 0;
        }

        @Override
        public String getMimeType(String file) {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Set getResourcePaths(String path) {
            return null;
        }

        @Override
        public URL getResource(String path) throws MalformedURLException {
            return null;
        }

        @Override
        public InputStream getResourceAsStream(String path) {
            return null;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return null;
        }

        @Override
        public RequestDispatcher getNamedDispatcher(String name) {
            return null;
        }

        @Override
        public Servlet getServlet(String name) throws ServletException {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Enumeration getServlets() {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Enumeration getServletNames() {
            return null;
        }

        @Override
        public void log(String msg) {
        }

        @Override
        public void log(Exception exception, String msg) {
            
        }

        @Override
        public void log(String message, Throwable throwable) {
        }

        @Override
        public String getRealPath(String path) {
            return "src/test/resources/filesource";
        }

        @Override
        public String getServerInfo() {
            return null;
        }

        @Override
        public String getInitParameter(String name) {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Enumeration getInitParameterNames() {
            return null;
        }

        @Override
        public Object getAttribute(String name) {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Enumeration getAttributeNames() {
            return null;
        }

        @Override
        public void setAttribute(String name, Object object) {
        }

        @Override
        public void removeAttribute(String name) {
        }

        @Override
        public String getServletContextName() {
            return null;
        }
        
    }
}
