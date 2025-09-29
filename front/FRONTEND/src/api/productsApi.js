import axiosClient from '../hooks/axiosClient';

/**
 * Busca todos los productos con filtros opcionales (para el catálogo).
 * @param {object} filters - Un objeto con los filtros a aplicar.
 * @returns {Promise<any>}
 */
export const getProducts = async (filters) => {
  try {
    const { data } = await axiosClient.get('/products/', { params: filters });
    return data;
  } catch (error) {
    console.error('Error fetching products:', error);
    throw error;
  }
};

/**
 * Busca un único producto por su ID.
 * @param {number | string} id - El ID del producto.
 * @returns {Promise<any|null>}
 */
export const getProductById = async (id) => {
  // Blindaje para que no explote si el ID llega como 'undefined'
  if (!id) {
    console.warn('Se intentó buscar un producto sin ID.');
    return null;
  }
  try {
    // Llama a la ruta correcta que creamos en el backend
    const response = await axiosClient.get(`/products/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching product with id ${id}:`, error);
    throw error;
  }
};

/**
 * Crea un nuevo producto (función para admins).
 * OJO: Como subís imágenes, se usa FormData.
 * @param {FormData} productData - Los datos del producto en un FormData.
 * @returns {Promise<any>}
 */
export const createProduct = async (productData) => {
  try {
    const response = await axiosClient.post('/products/', productData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error creating product:', error);
    throw error;
  }
};

/**
 * Actualiza un producto existente (función para admins).
 * @param {number} id - El ID del producto a actualizar.
 * @param {object} productData - Los campos del producto a actualizar.
 * @returns {Promise<any>}
 */
export const updateProduct = async (id, productData) => {
  try {
    const response = await axiosClient.put(`/products/${id}`, productData);
    return response.data;
  } catch (error) {
    console.error(`Error updating product with id ${id}:`, error);
    throw error;
  }
};

/**
 * Elimina un producto (función para admins).
 * @param {number} id - El ID del producto a eliminar.
 * @returns {Promise<any>}
 */
export const deleteProduct = async (id) => {
  try {
    const response = await axiosClient.delete(`/products/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting product with id ${id}:`, error);
    throw error;
  }
};