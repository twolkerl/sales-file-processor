package com.twl.salesfileprocessor.util;

import com.twl.salesfileprocessor.model.Cliente;
import com.twl.salesfileprocessor.model.Item;
import com.twl.salesfileprocessor.model.Venda;
import com.twl.salesfileprocessor.model.Vendedor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Conversor que converte os campos recebidos no arquivo para o respectivo objeto.
 *
 * @author tiago.wolker
 * @since 22/09/2019
 */
public class ConverterUtils {

    /**
     * Converte os campos do arquivo para um {@link Vendedor}.
     *
     * @param fields Campos recebidos no arquivo. Devem ser recebidos na seguinte ordem separados por <b>vírgula</b>:
     *               <ul>
     *                  <li>
     *                      [0] = Tipo
     *                  </li>
     *                  <li>
     *                      [1] = CPF
     *                  </li>
     *                  <li>
     *                      [2] = Nome
     *                  </li>
     *                  <li>
     *                      [3] = Salário
     *                  </li>
     *               </ul>
     * @return {@link Vendedor}
     */
    public static Vendedor convertToVendedor(String[] fields) {
        return new Vendedor(fields[1], fields[2], new BigDecimal(fields[3]));
    }

    /**
     * Converte os campos do arquivo para um {@link Cliente}.
     *
     * @param fields Campos recebidos no arquivo. Devem ser recebidos na seguinte ordem separados por <b>vírgula</b>:
     *               <ul>
     *                  <li>
     *                      [0] = Tipo
     *                  </li>
     *                  <li>
     *                      [1] = CNPJ
     *                  </li>
     *                  <li>
     *                      [2] = Nome
     *                  </li>
     *                  <li>
     *                      [3] = Área de Negócio
     *                  </li>
     *               </ul>
     * @return {@link Cliente}
     */
    public static Cliente convertToCliente(String[] fields) {
        return new Cliente(fields[1], fields[2], fields[3]);
    }


    /**
     * Converte os campos do arquivo para um {@link Venda}.
     *
     * @param fields Campos recebidos no arquivo. Devem ser recebidos na seguinte ordem separados por <b>vírgula</b>:
     *               <ul>
     *                  <li>
     *                      [0] = Tipo
     *                  </li>
     *                  <li>
     *                      [1] = ID da Venda
     *                  </li>
     *                  <li>
     *                      [2] = Itens da venda [ID do Item-Qtd Item-Preço Item, ...]
     *                  </li>
     *                  <li>
     *                      [3] = Nome do Vendedor
     *                  </li>
     *               </ul>
     * @return {@link Venda}
     */
    public static Venda convertToVenda(String[] fields) {

        String itemStr = fields[2];

        String[] itens = itemStr.split(",");

        List<Item> itemList = convertToItens(itens);

        return new Venda(Long.valueOf(fields[1]), itemList, fields[3]);
    }


    /**
     * Converte os campos do arquivo para um {@link Item} pertencente à {@link Venda}.
     *
     * @param itemArray Campos recebidos no arquivo. Devem ser recebidos na seguinte ordem separados por <b>hífen</b>:
     *               <ul>
     *                  <li>
     *                      [0] = ID do Item
     *                  </li>
     *                  <li>
     *                      [1] = Qtd Item
     *                  </li>
     *                  <li>
     *                      [2] = Preço Item
     *                  </li>
     *               </ul>
     * @return {@link Item} pertencente à {@link Venda}
     */
    public static List<Item> convertToItens(String[] itemArray) {

        List<Item> itens = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] itemFields = itemArray[i].split("-");
            itens.add(new Item(Long.valueOf(itemFields[0].replaceAll("[\\[\\]]", "")),
                    Integer.valueOf(itemFields[1]),
                    new BigDecimal(itemFields[2].replaceAll("[\\[\\]]", ""))));
        }

        return itens;
    }
}
